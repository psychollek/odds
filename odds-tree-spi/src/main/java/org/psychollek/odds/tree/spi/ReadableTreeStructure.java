/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psychollek.odds.tree.spi;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author psychollek
 */
public interface ReadableTreeStructure {
    
    /**
     * This method locks the tree for all operations resulting in calling any of 
     * it's TreeStructureListeners.
     * This method should be used when one wants to make a snapshot of a tree.
     * This method has to return anly after all currently performed actions 
     * resulting in calling TreeStructureListeners have been completed.
     * Method will return object which will have to be passed to unlockTree, this 
     * way many threads can call for locking to perform snapshot and be sure lock 
     * will be held until they finish operation.
     * The only guarantee, tree implementation must uphold is that, no 
     * TreeStructureListener will be called until unlockTree(o) will be called - 
     * it does not mean that tree cannot be updated at a time.
     * @return object on which lock has been created.
     */
    Object lockTree();
    
    /**
     * This method instructs tree, that it can release the lock held.
     * Method return confirms that this lock has been released, but there might 
     * be other locks held.
     * @param lock 
     */
    void unlockTree(Object lock);

    ReadableNode getRootNode();
    
    void addTreeStructureListener(TreeStructureListener l);
    
    void removeTreeStructureListener(TreeStructureListener l);

    interface ReadableNode {

        /**
         * @return full id of the node.
         */
        String getId();

        /**
         * @return  current value of node.
         */
        Serializable getValue();

        List<ReadableNode> getChildren();

    }

    interface TreeStructureListener {
        
        void nodeValueChanged(String id, Serializable newValue);
        
        void nodeRemoved(String id);
        
        void nodeAdded(String id);
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psychollek.odds.tree.spi;

import java.io.Serializable;

/**
 *
 * @author psychollek
 */
public interface WritableTreeStructure {

    /**
     * returns root node
     *
     * @return
     */
    WritableNode getRootNode();

    
    WritableNode getNodeById(String id);

    /**
     * returns parent of the given node.
     * @param id
     * @throws IllegalArgumentException if root node has been requested or id.
     * @return
     */
    WritableNode getParentOfNodeById(String id) throws IllegalArgumentException;

    public interface WritableNode {

        /**
         * Creates new node as a child of current.
         *
         * @param id fully qualified id of the new node.
         * @throws IllegalArgumentException if id is not conforming to naming
         * scheme or id does not indicate this node as a parent.
         * @return newly created Child node. *
         */
        WritableNode createNewChild(String id) throws IllegalArgumentException;

        /**
         * deletes child of this node and
         *
         * @param id
         * @throws IllegalArgumentException if id is not conforming to naming
         * scheme or id does not indicate this node as a parent.
         */
        void deleteChild(String id) throws IllegalArgumentException;

        void setValue(Serializable value);

    }
}

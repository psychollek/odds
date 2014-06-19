/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.psychollek.odds.transport.api;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author psychollek
 */
public interface Message {
    
    /**
     * @see MessageType
     * @return type of message
     */
    MessageType getType();
    
    /**
     * Original state, the updates should be performed on.
     * @return number of state updates should start to work on.
     */
    public Long getOriginalState();
    
    /**
     * State that the tree will be after performing updates.
     * @return number of state after updates.
     */
    public Long getResultingState();
    
    /**
     * Returns list of changes that should be performed by reciepent.
     * changes will be performed in order they are in the list, but if possible 
     * this should not matter and sender should perform basic optimization 
     * before sending.
     * @return list of changes that should be performed.
     */
    public List<Change> getChanges();
    
    /**
     * Enum for indicating the intent of the message.
     */
    public static enum MessageType{
        /**
         * Type indicating a fresh state.
         * getOriginalState() has to return 0;
         * Reciepent will flush his copy of tree and apply all changes.
         * getChanges() should return only PUT changes.
         */
        STATE,
        /**
         * Type indicating state update.
         */
        STATE_UPDATE,
        /**
         * Type indicating that reciepient cannot perform an update or doesn't have any tree.
         * getOriginalState() has to return the state sender of the message has.
         * getResultingState() has to return state getOriginalState() form the 
         * message that triggered LOST or null if LOST is sent as standalone message.
         * getChanges() should return {@link Collections#emptyList()}.
         */
        LOST
    }        
    
}

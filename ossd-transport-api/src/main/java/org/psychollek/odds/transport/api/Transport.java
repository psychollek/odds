/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.psychollek.odds.transport.api;

/**
 * This is a contract any implementation of transport need to conform to.
 * 
 * @author psychollek
 */
public interface Transport {
    
    /**
     * This method sends a multicast message to all members connected to transport.
     * Depending on transport configuration it should return either when message
     * is sure to be sent if transport is configured to not wait for reciepent 
     * responses or when it is sure to be delivered if transport is configured to
     * wait for all responses.
     * @param message to be sent.
     */
    void sendMessage(Message message);
    
    /**
     * This method sends a unicast message to original message author.
     * this  method should return after transport is sure, message has been 
     * delivered.
     * @param response message to be sent.
     * @param original message from which author will be deduced.
     */
    void respondToMessage(Message response,Message original);
    
    void addTransportListener(TransportListener tr);
    
    void removeTransportListener(TransportListener tr);        
    
    public interface TransportListener{
        
        /**
         * This method is called when transport implementation recieves a message.
         * it shall be called after eventual confirmation has been sent - thus 
         * the call should not block transport operation, for example it should 
         * be possible to respond to the Message m from within this method.
         * @param m 
         */
        void messageArrived(Message m);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.psychollek.odds.transport.api;

import java.io.Serializable;

/**
 *
 * @author psychollek
 */
public interface Change {
    
    ChangeType getType();
    
    String getSubjectId();
    
    Serializable getValue();
    
    public enum ChangeType{
        PUT,
        DELETE,        
    }
    
}

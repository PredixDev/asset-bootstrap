/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
 
package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.LinkedHashMap;

import com.ge.predix.entity.model.Model;

/**
 * 
 * @author predix -
 */
public class LinkedHashMapModel extends Model
{

    /**
     * 
     */
    private static final long serialVersionUID = 3999808629843801728L;
    private LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
    /**
     * @return -
     */
    public LinkedHashMap<String,Object> getMap()
    {
        return this.map;
    }
    /**
     * @param map -
     */
    public void setMap(LinkedHashMap<String, Object> map)
    {
       this.map = map;
    }
}

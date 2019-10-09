package com.rellum.moovme.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Tarifario {
    private HashMap<String, HashMap<TipoDeActivo,Integer>>lista;
    private HashSet<String>zonas;

    public Tarifario() {
        lista=new HashMap<String, HashMap<TipoDeActivo, Integer>>();
        zonas = new HashSet<String>();

    }

    public void agregarTarifa(Activo activo, String zona, int precio){

            if (!lista.keySet().contains(zona)) {
                HashMap<TipoDeActivo, Integer> temp = new HashMap<TipoDeActivo, Integer>();
                temp.put(activo.getType(), precio);
                lista.put(zona, temp);
                zonas.add(zona);
            } else {
                if (!lista.get(zona).containsKey(activo.getType())) {
                    HashMap<TipoDeActivo, Integer> temp = lista.get(zona);
                    temp.put(activo.getType(), precio);
                    lista.put(zona, temp);
                }else {
                    throw new RuntimeException("Tarifa ya agregada");
                }
            }

    }

    public void actualizarTarifa(Activo activo, String zona, int nuevoPrecio){
        if (lista.keySet().contains(zona)){
            if (lista.get(zona).keySet().contains(activo.getType())){
                lista.get(zona).replace(activo.getType(),nuevoPrecio);
            }else{
                throw new RuntimeException("Activo no encontrado para la zona: "+zona);
            }
        }else{
            throw new RuntimeException("Zona "+zona+" no encontrada");
        }

    }
    public int getPrice(Activo activo, String zona){
        if (lista.keySet().contains(zona)){
            if (lista.get(zona).keySet().contains(activo.getType())){
                return lista.get(zona).get(activo.getType());
            }
        }
        throw new RuntimeException("Precio no encontrado");
    }

    public ArrayList<String> getZonasList(){
        ArrayList<String>returnResult=new ArrayList<String >();
        Iterator value = this.zonas.iterator();
        while (value.hasNext()){
            returnResult.add(value.next().toString());
        }
        return returnResult;
    }
}
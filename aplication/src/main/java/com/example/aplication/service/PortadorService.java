package com.example.aplication.service;


import com.example.aplication.model.TesteModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juniorbraga on 28/03/17.
 */

public interface PortadorService {

    @GET("carros/marcas.json")
    Call<ArrayList<TesteModel>> buscarUsuario();
}

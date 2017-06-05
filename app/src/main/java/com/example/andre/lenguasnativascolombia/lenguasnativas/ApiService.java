package com.example.andre.lenguasnativascolombia.lenguasnativas;

import com.example.andre.lenguasnativascolombia.models.Lenguas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andre on 4/06/2017.
 */

public interface ApiService {

    @GET("y5wk-q9yj.json")
    Call<List<Lenguas>> obtenerListaDepartamentos();

}

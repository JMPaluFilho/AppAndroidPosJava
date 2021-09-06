package com.example.postcontrol.persistencia;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.postcontrol.entity.Empresa;

import java.util.List;

@Dao
public interface EmpresaDAO {

    @Insert
    long insert(Empresa empresa);

    @Delete
    void delete(Empresa empresa);

    @Update
    void update(Empresa empresa);

    @Query("SELECT * FROM empresa WHERE id = :id")
    Empresa findEmpresaById(long id);

    @Query("SELECT * FROM empresa")
    List<Empresa> findAll();
}

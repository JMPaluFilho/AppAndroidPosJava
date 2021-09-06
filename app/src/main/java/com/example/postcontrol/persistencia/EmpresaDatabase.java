package com.example.postcontrol.persistencia;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.postcontrol.entity.Empresa;

@Database(entities = {Empresa.class}, version = 1, exportSchema = false)
public abstract class EmpresaDatabase extends RoomDatabase {

    public abstract EmpresaDAO empresaDAO();

    private static EmpresaDatabase instance;

    public static EmpresaDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (EmpresaDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            EmpresaDatabase.class,
                            "empresas.db")
                            .allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}

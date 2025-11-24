package com.senac.gestorpessoaladmimconfig.model.DAO;

import com.senac.gestorpessoaladmimconfig.model.Usuario;
import jakarta.persistence.EntityManager;

public class UsuarioDAO {

    private EntityManager entityManager;

    public UsuarioDAO (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void salvar(Usuario usuario){
        entityManager.getTransaction().begin();

        entityManager.persist(usuario);

        entityManager.getTransaction().commit();
    }
}

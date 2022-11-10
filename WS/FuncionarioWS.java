/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import DAO.Funcionario;
import DAO.FuncionarioDao;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * REST Web Service
 *
 * @author enilson
 */
@Path("rest")
public class FuncionarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionarioWS
     */
    public FuncionarioWS() {
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios")
    public String getFuncionarios() {
        
        FuncionarioDao dao = new FuncionarioDao ();
        List<Funcionario> listaFuncionarios = dao.getLista();
        Gson gson = new Gson ();
        
        return gson.toJson(listaFuncionarios);
    }
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios/{numat}")
    public String getFuncionario(@PathParam("numat") int id) throws SQLException {
        
        FuncionarioDao dao = new FuncionarioDao ();
        Funcionario funcionario = dao.consulta(id);
        if(funcionario != null){
        Gson gson = new Gson ();
        
        return gson.toJson(funcionario);
        }
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("supervisores")
    public String getSupervisores() {
        
        FuncionarioDao dao = new FuncionarioDao ();
        List<Funcionario> listaFuncionarios = dao.getListaSupervisores();
        Gson gson = new Gson ();
        
        return gson.toJson(listaFuncionarios);
    }
   
    /**
     * PUT method for updating or creating an instance of FuncionarioWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}

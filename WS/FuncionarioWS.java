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
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    
    @DELETE
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios/{numat}")  
    public Response delFuncionario(@PathParam("numat")int id) {
        FuncionarioDao dao = new FuncionarioDao();
        // testa se encontrou e removeu o funcionario
        if(dao.remove(id)){
            return Response.status(Response.Status.OK).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios")
    public Response addFuncionario(String content){
        Gson gson = new Gson();
        Funcionario funcionario = (Funcionario) gson.fromJson(content, Funcionario.class);
        FuncionarioDao dao = new FuncionarioDao();
        // tenta inserir novo funcionario
        try{
            dao.adiciona(funcionario);
        }
        catch(RuntimeException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }
   
    
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios")
    public Response setFuncionario(String content) {
        Gson gson = new Gson();
        //utiliza do método fromJson() 
        //para converter a String informada por parâmetro
        Funcionario funcionario = (Funcionario) gson.fromJson(content, Funcionario.class);
        FuncionarioDao dao = new FuncionarioDao();
        try{
            //testa se conseguiu atualizar o funcionário
            if (dao.atualiza(funcionario))
                return Response.status(Response.Status.OK).build();
            else 
                return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch(RuntimeException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
    }
}

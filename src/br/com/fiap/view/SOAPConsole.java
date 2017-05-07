package br.com.fiap.view;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import br.com.fiap.natura.bo.UsuarioBOStub;
import br.com.fiap.natura.bo.UsuarioBOStub.Cadastrar;
import br.com.fiap.natura.bo.UsuarioBOStub.Deletar;
import br.com.fiap.natura.bo.UsuarioBOStub.Listar;
import br.com.fiap.natura.bo.UsuarioBOStub.ListarResponse;
import br.com.fiap.natura.bo.UsuarioBOStub.TipoLogin;
import br.com.fiap.natura.bo.UsuarioBOStub.TipoUsuario;
import br.com.fiap.natura.bo.UsuarioBOStub.Usuario;

public class SOAPConsole {
	
	public static void main(String[] args) throws RemoteException {
		try {
			UsuarioBOStub stub = new UsuarioBOStub();
			Cadastrar cadastrar = new Cadastrar();
			
			TipoLogin tipoLogin = new TipoLogin();
			tipoLogin.setDescricao("Local");
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setDescricao("Consultora");
			
			Usuario usuario = new Usuario();
			usuario.setCodigoFacebook(0);
			usuario.setLogin("teste");
			usuario.setSenha("teste");
			usuario.setTipoLogin(tipoLogin);
			usuario.setTipoUsuario(tipoUsuario);
			cadastrar.setUsuario(usuario);
			stub.cadastrar(cadastrar);
			System.out.println("Cadastrado com sucesso");
			
			ListarResponse resp = stub.listar(new Listar());
			Usuario[] usuarios = resp.get_return();
			for(Usuario u : usuarios){
				System.out.println("Cód.: " + u.getCodigo() + "\n"
								+ "Login: " + u.getLogin() + "\n"
								+ "Senha: " + u.getSenha() + "\n" 
								+ "Tipo Login: " + u.getTipoLogin().getDescricao() + "\n" 
								+ "Tipo Usuário: "+ u.getTipoUsuario().getDescricao());
			}
			
			/*
			Deletar deletar = new Deletar();
			deletar.setCodigo(10);
			stub.deletar(deletar);
			System.out.println("Deletado com sucesso");
			*/
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}
}

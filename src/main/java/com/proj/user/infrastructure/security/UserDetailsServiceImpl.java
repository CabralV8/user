package com.proj.user.infrastructure.security;


import com.proj.user.infrastructure.entity.User;
import com.proj.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Anotação que registra essa classe como um componente de serviço no Spring
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired // Injeta automaticamente uma instância de UserRepository
    private UserRepository userRepository;

    /**
     * Método que carrega os dados do usuário com base no e-mail informado.
     * Utilizado pelo Spring Security no processo de autenticação.
     *
     * @param email e-mail do usuário a ser autenticado
     * @return UserDetails com as informações de autenticação (usuário, senha, roles)
     * @throws UsernameNotFoundException se o e-mail não for encontrado no banco
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados. Caso não encontre, lança exceção.
        User usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Cria e retorna um objeto UserDetails com o e-mail, a senha e uma autoridade padrão ("USER")
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())  // Nome de login
                .password(usuario.getPassword())   // Senha já criptografada
                .authorities("USER")               // Papel/autoridade do usuário (fixo aqui)
                .build();
    }
}

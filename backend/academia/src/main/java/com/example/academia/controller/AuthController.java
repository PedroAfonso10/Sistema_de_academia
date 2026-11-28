package com.example.academia.controller;

import com.example.academia.dto.request.AlterarSenhaRequestDTO;
import com.example.academia.dto.request.CriarContaRequestDTO;
import com.example.academia.dto.request.LoginRequestDTO;
import com.example.academia.dto.response.LoginResponseDTO;
import com.example.academia.security.UsuarioAutenticado;
import com.example.academia.service.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AutenticacaoService autenticacaoService;

    @PutMapping("/contas/{pessoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void criarConta(@AuthenticationPrincipal UsuarioAutenticado solicitante,
                           @PathVariable Long pessoaId,
                           @Valid @RequestBody CriarContaRequestDTO dto) {
        autenticacaoService.criarConta(solicitante, pessoaId, dto.getSenha());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO){
        String token = autenticacaoService.login(requestDTO.getIdentificador(), requestDTO.getSenha());
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponseDTO.builder().token(token).build());
    }

    @PutMapping("/senha/{pessoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@AuthenticationPrincipal UsuarioAutenticado solicitante,
                             @RequestBody @Valid AlterarSenhaRequestDTO dto) {
        autenticacaoService.alterarSenha(solicitante, dto.getSenhaAtual(), dto.getNovaSenha());
    }

}

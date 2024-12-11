package com.desafiomv.services;

import com.desafiomv.dtos.EmpresaDTO;
import com.desafiomv.entidades.Conta;
import com.desafiomv.entidades.Empresa;
import com.desafiomv.repositorios.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpresaService {


    final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }


    public List<Empresa> listarEmpresas() {

        return empresaRepository.findAll();
    }



    @Transactional
    public Empresa criar(EmpresaDTO empresaDTO) {

        if(empresaRepository.findEmpresaByCnpj(empresaDTO.cnpj()).isPresent()){
            throw new RuntimeException("CNPJ já cadastrado");
        }

        if(empresaRepository.findEmpresaByEmail(empresaDTO.email()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }

        if (empresaRepository.findEmpresaByNome(empresaDTO.nome()).isPresent()) {
            throw new RuntimeException("Nome já cadastrado");
        }

        Empresa empresa = new Empresa();

        empresa.setNome(empresaDTO.nome());
        empresa.setEmail(empresaDTO.email());
        empresa.setCnpj(empresaDTO.cnpj());

        empresaDTO.contas().forEach(contaDto -> {
            Conta conta = new Conta();
            conta.setCodigoAgencia(contaDto.codigoAgencia());
            conta.setDigitoConta(contaDto.digitoConta());
            conta.setNumeroConta(contaDto.numeroConta());
            conta.setDigitoAgencia(contaDto.digitoAgencia());
            conta.setTipoConta(contaDto.tipoConta());
            conta.setCodigoBanco(contaDto.codigoBanco());
            conta.setEmpresa(empresa);
            empresa.addContas(conta);
        });

        return empresaRepository.save(empresa);
    }

    public Empresa buscarPorCnpj(String cnpj) {
        return empresaRepository.findEmpresaByCnpj(cnpj).orElseThrow(() -> {
             throw new RuntimeException("Empresa não encontrada");
        });
    }


}

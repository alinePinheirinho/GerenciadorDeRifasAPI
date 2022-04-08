package br.com.pinheirostec.gerenciadorrifaonline.service;

import br.com.pinheirostec.gerenciadorrifaonline.dto.CampoRifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.dto.RifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.entity.CampoRifa;
import br.com.pinheirostec.gerenciadorrifaonline.entity.CompradorCampoRifa;
import br.com.pinheirostec.gerenciadorrifaonline.entity.Proprietario;
import br.com.pinheirostec.gerenciadorrifaonline.entity.Rifa;
import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusCampoRifa;
import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusRifa;
import br.com.pinheirostec.gerenciadorrifaonline.exceptions.NegocioException;
import br.com.pinheirostec.gerenciadorrifaonline.mapper.RifaMapper;
import br.com.pinheirostec.gerenciadorrifaonline.repository.CampoRifaRepository;
import br.com.pinheirostec.gerenciadorrifaonline.repository.CompradorRepository;
import br.com.pinheirostec.gerenciadorrifaonline.repository.ProprietarioRepository;
import br.com.pinheirostec.gerenciadorrifaonline.repository.RifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RifaServiceImpl implements RifaService {

    @Autowired
    RifaRepository rifaRepository;

    @Autowired
    CampoRifaRepository campoRifaRepository;

    @Autowired
    ProprietarioRepository proprietarioRepository;

    @Autowired
    CompradorRepository compradorRepository;

    @Autowired
    RifaMapper rifaMapper;

    @Override
    public void cadastraRifa(RifaDto rifaDto) {

        Rifa rifa = rifaMapper.paraRifa(rifaDto);
        Proprietario proprietarioEntity = buscaProprietarioNaBase(rifaDto);

        if (Objects.isNull(proprietarioEntity)){
            proprietarioEntity = proprietarioRepository.save(rifa.getProprietario());
        }
        rifa.setProprietario(proprietarioEntity);
        rifaRepository.save(rifa);

        List<CampoRifa> campoRifas = rifaMapper.paraCampoRifa(rifaDto.getCamposRifa());
        campoRifas.forEach(campo -> campo.setRifa(rifa));
        campoRifaRepository.saveAll(campoRifas);
    }

    @Override
    public RifaDto buscaRifa(Long id) throws NegocioException {
        Rifa rifa = rifaRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Nao foi localizado uma Rifa cadastrada com o ID Informado. id= " + id));
        List<CampoRifa> camposRifa = campoRifaRepository.findByRifa(rifa);
        return converteRifaDtoComCampos(rifa, camposRifa);
    }

    @Override
    public List<RifaDto> buscaRifasPorProprietario(Long id) throws NegocioException {
        Proprietario proprietario = proprietarioRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Não foi Localizado um Proprietario com esse ID"));

        List<Rifa> rifasDoProprietario = rifaRepository.findByProprietario(proprietario);

        if(rifasDoProprietario == null || rifasDoProprietario.isEmpty()) {
            throw new NegocioException("Não foi Localizado Rifas para Proprietario com esse ID");
        }
        return rifasDoProprietario.stream()
                .map(rifa -> converteRifaDtoComCampos(rifa, campoRifaRepository.findByRifa(rifa)))
                .collect(Collectors.toList());
    }

    @Override
    public Page<RifaDto> buscarTodasRifasExistentes(StatusRifa statusRifa, Pageable page) {
        Page<Rifa> rifaPage = rifaRepository.findByStatusRifa(statusRifa, page);
        return rifaPage.map(rifa -> rifaMapper.paraRifaDto(rifa));
    }

    @Override
    public void compraCamposRifa(Long id, List<CampoRifaDto> camposRifaDto) {
        List<CampoRifa> campoRifas = rifaMapper.paraCampoRifa(camposRifaDto);
        campoRifas.forEach(campoRifa -> {
            CampoRifa campoEntity = atualizaStatusCampoRifa(campoRifa, StatusCampoRifa.PENDENTE_APROVACAO);
            campoEntity.setComprador(atualizaCompradorCampoRifa(campoRifa));
            campoRifaRepository.save(campoEntity);
        });
    }

    @Override
    public void confirmaCompraCamposRifa(Long id, List<CampoRifaDto> camposRifaDto) {
        List<CampoRifa> campoRifas = rifaMapper.paraCampoRifa(camposRifaDto);
        campoRifas.forEach(campoRifa ->
            campoRifaRepository.save(atualizaStatusCampoRifa(campoRifa, StatusCampoRifa.OCUPADO)));
    }

    private CompradorCampoRifa atualizaCompradorCampoRifa(CampoRifa campoRifa) {
        CompradorCampoRifa compradorEntity = compradorRepository.
                findFirstByNomeAndTelefone(campoRifa.getComprador().getNome(), campoRifa.getComprador().getTelefone());
        compradorEntity = Objects.nonNull(compradorEntity) ? compradorEntity : campoRifa.getComprador();
        return compradorEntity;
    }

    private CampoRifa atualizaStatusCampoRifa(CampoRifa campoRifa, StatusCampoRifa status) {
        CampoRifa campoEntity = campoRifaRepository.findById(campoRifa.getId())
                .orElseThrow(() -> new RuntimeException("Campo nao localizado na base de dados"));
        campoEntity.setStatusCampo(status);
        return campoEntity;
    }

    private RifaDto converteRifaDtoComCampos(Rifa rifa, List<CampoRifa> camposRifa) {
        RifaDto rifaDto = rifaMapper.paraRifaDto(rifa);
        List<CampoRifaDto> campoRifaDto = rifaMapper.paraCampoRifaDto(camposRifa);
        rifaDto.setCamposRifa(campoRifaDto);
        return rifaDto;
    }

    private Proprietario buscaProprietarioNaBase(RifaDto rifaDto) {
       return proprietarioRepository
               .findByNomeAndTelefone(rifaDto.getProprietario().getNome(), rifaDto.getProprietario().getTelefone());
    }
}

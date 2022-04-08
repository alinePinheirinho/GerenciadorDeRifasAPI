package br.com.pinheirostec.gerenciadorrifaonline.controller;

import br.com.pinheirostec.gerenciadorrifaonline.dto.CampoRifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.dto.RifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusRifa;
import br.com.pinheirostec.gerenciadorrifaonline.exceptions.NegocioException;
import br.com.pinheirostec.gerenciadorrifaonline.service.RifaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

@RestController
@RequestMapping("/rifa")
@Api(value = "Controller de Rifa")
public class RifaController {

    @Autowired
    private RifaService rifaService;

    @ApiOperation(value = "Endpoint para cadastrar Rifa")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "cadastrar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cadastrarRifa(@RequestBody @Valid RifaDto rifaDto) {
        rifaService.cadastraRifa(rifaDto);
    }

    @ApiOperation(value = "Endpoint para buscar Rifa ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RifaDto cadastrarRifa(@PathVariable @NotNull Long id) throws NegocioException {
        return rifaService.buscaRifa(id);
    }

    @ApiOperation(value = "Endpoint buscar lista  de Rifas do Proprietario")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "proprietario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RifaDto> buscarRifasPorProprietario(@PathVariable @NotNull Long id) throws NegocioException {
        return rifaService.buscaRifasPorProprietario(id);
    }

    @ApiOperation(value = "Endpoint buscar lista  Todas Rifas existentes no sistema")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/todas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<RifaDto> buscarTodasRifasExistentes(
            @RequestParam(value = "statusRifa") @NotNull StatusRifa statusRifa,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 30) Pageable page
    ) {
        return rifaService.buscarTodasRifasExistentes(statusRifa, page);
    }

    @ApiOperation(value = "Endpoint comprar Campos de uma Rifa")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "{id}/compraCampos", produces = MediaType.APPLICATION_JSON_VALUE)
    public void compraCamposRifa(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid List<CampoRifaDto> camposRifaDto
            ) {
        rifaService.compraCamposRifa(id, camposRifaDto);
    }

    @ApiOperation(value = "Endpoint confirma a comprar Campos de uma Rifa")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "{id}/confirmaCompra", produces = MediaType.APPLICATION_JSON_VALUE)
    public void confirmaCompraCamposRifa(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid List<CampoRifaDto> camposRifaDto
    ) {
        rifaService.confirmaCompraCamposRifa(id, camposRifaDto);
    }
}

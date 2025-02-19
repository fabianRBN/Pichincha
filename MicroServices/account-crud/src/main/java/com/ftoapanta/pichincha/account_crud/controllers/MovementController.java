package com.ftoapanta.pichincha.account_crud.controllers;

import com.ftoapanta.pichincha.account_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.account_crud.dto.MovementResponseDTO;
import com.ftoapanta.pichincha.account_crud.dto.MovementResquestDTO;
import com.ftoapanta.pichincha.account_crud.entities.Account;
import com.ftoapanta.pichincha.account_crud.entities.Movement;
import com.ftoapanta.pichincha.account_crud.services.AccountService;
import com.ftoapanta.pichincha.account_crud.services.MovementService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(path = "transaction")
@Validated
public class MovementController {
    private final MovementService movementService;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> createTransaction(@RequestBody MovementResquestDTO movementResquestDTO) {

        return ResponseEntity.ok(movementService.createTransaction(movementResquestDTO));
    }
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Movement>> getMovementsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(movementService.getTransactionsByAccountId(accountId));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<BaseResponseDTO> getMovementsByClientId(@PathVariable Long clientId) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            List<MovementResponseDTO> movements = movementService.getMovementsByClientId(clientId);
            baseResponseDTO.setData(movements);
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setSuccess(false);
            baseResponseDTO.setMessage(e.getMessage());
        }


        return ResponseEntity.ok(baseResponseDTO);
    }
    @GetMapping("/findByDateBetween")
    public ResponseEntity<BaseResponseDTO> findByDateBetween(@RequestParam(required = false)
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                 @PastOrPresent(message = "La fecha de inicio no puede ser futura.")
                                                                 LocalDate start,

                                                             @RequestParam(required = false)
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd")

                                                                 LocalDate end,

                                                             @RequestParam(defaultValue = "0")
                                                                 @Min(value = 0, message = "El número de página no puede ser negativo.")
                                                                 int page,

                                                             @RequestParam(defaultValue = "10")
                                                                 @Min(value = 1, message = "El tamaño de página debe ser al menos 1.")
                                                                 @Max(value = 100, message = "El tamaño de página no puede superar 100.")
                                                                 int size)
    {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            Map<String,Object> map =  movementService.findByDateBetween(start,end, PageRequest.of(page, size));
            baseResponseDTO.setData(map.get("result"));
            baseResponseDTO.setSuccess(true);
            baseResponseDTO.setTotalElements((Long) map.get("total"));

        }catch (Exception e){
            baseResponseDTO.setSuccess(false);
            baseResponseDTO.setMessage(e.getMessage());
        }

        return ResponseEntity.ok(baseResponseDTO);
    }
}

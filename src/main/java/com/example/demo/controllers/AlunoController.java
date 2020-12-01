package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Aluno;
import com.example.demo.repositories.AlunosRepository;

@RestController 
@RequestMapping("/api/alunos")
public class AlunoController {
	
	@Autowired
	private AlunosRepository alunosRepository;
			
	@PostMapping
	public ResponseEntity<Aluno> create(@Validated @RequestBody Aluno aluno) {
		alunosRepository.save(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
	}
	
	@PostMapping("/importarNotas")
	@Transactional
	public void updatePlan(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
		
		    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		    XSSFSheet sheetAt = workbook.getSheetAt(0);
		    
		    for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
		    	
		    	Aluno planAluno = new Aluno(); 
		    	XSSFRow row = sheetAt.getRow(i);
		    	
		    	planAluno.setNome(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "Not a registered name");
		    	planAluno.setAno(row.getCell(1) != null ? (int) row.getCell(1).getNumericCellValue() : 0); 
		    	planAluno.setMateria(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "No matter registered");
		    	planAluno.setNotap1(row.getCell(3) != null ? (int) row.getCell(3).getNumericCellValue() : 0) ;
		    	planAluno.setNotap2(row.getCell(4) != null ? (int) row.getCell(4).getNumericCellValue() : 0) ;
		    	planAluno.setNotap3(row.getCell(5)!= null ? (int) row.getCell(5).getNumericCellValue() : 0) ;
		    	
		    	int media = planAluno.getNotap1() + planAluno.getNotap2() + planAluno.getNotap3() /3;
		    	planAluno.setMedia(media);
		    	
		    	alunosRepository.save(planAluno); 
		    			    	
		    }
		
	}
	
	@GetMapping("/recuperarNotasAula")
	public ResponseEntity<List<Aluno>> alunosJson() {
		List<Aluno> list = alunosRepository.findAll();
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> listAlunos() {
		List<Aluno> listAll = alunosRepository.findAll();
		return ResponseEntity.ok(listAll);
		
	}

	
	@GetMapping("{id}")
	public ResponseEntity<Optional<Aluno>> findId(@PathVariable Long id) {
		Optional<Aluno> aluno = alunosRepository.findById(id);
	 	return ResponseEntity.ok(aluno);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAluno(@PathVariable Long id) {
		alunosRepository.deleteById(id);
		
	}
	
	@PutMapping("{id}") 
	public ResponseEntity<Aluno> update(@Validated @RequestBody Aluno aluno, @PathVariable Long id) {
		if (alunosRepository.findById(id).isPresent()) {
			aluno.setId(id);
			Aluno alunoUpdated = alunosRepository.save(aluno);
			return ResponseEntity.status(HttpStatus.OK).body(alunoUpdated);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(aluno);
		
	}
		

}			
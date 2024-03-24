package app.vercel.leonanthomaz.springcurriculum.controller;

import app.vercel.leonanthomaz.springcurriculum.component.PDFGenerator;
import app.vercel.leonanthomaz.springcurriculum.model.Usuario;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

/**
 * Controlador responsável por manipular requisições relacionadas à geração de currículos em PDF.
 */
@RestController
@RequestMapping("curriculum")
public class CurriculumController {
    private final PDFGenerator pdfGenerator;

    /**
     * Construtor do CurriculumController.
     *
     * @param pdfGenerator componente responsável por gerar o currículo em PDF.
     */
    @Autowired
    public CurriculumController(PDFGenerator pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
    }

    /**
     * Endpoint para gerar um currículo em PDF a partir dos dados do usuário fornecidos.
     *
     * @param usuario Objeto Usuario contendo os dados para o currículo.
     * @return ResponseEntity contendo o objeto Usuario e status HTTP 201 se o currículo foi gerado com sucesso.
     * @throws DocumentException    se houver erro ao manipular o documento PDF.
     * @throws FileNotFoundException se o arquivo PDF não for encontrado.
     */
    @PostMapping("/gerar-curriculum")
    public ResponseEntity<Usuario> gerarPDFCurriculo(@RequestBody Usuario usuario) throws DocumentException, FileNotFoundException {
        pdfGenerator.generateCurriculumPDF(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
package app.vercel.leonanthomaz.springcurriculum.controller;

import app.vercel.leonanthomaz.springcurriculum.component.PDFGenerator;
import app.vercel.leonanthomaz.springcurriculum.model.Usuario;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("curriculum")
public class CurriculumController {
    private final PDFGenerator pdfGenerator;

    @Autowired
    public CurriculumController(PDFGenerator pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
    }

//    @CrossOrigin(origins = "*") // Permite solicitações de qualquer origem
    @PostMapping("/gerar-curriculum")
    public ResponseEntity<Usuario> gerarPDFCurriculo(@RequestBody Usuario usuario) throws DocumentException, FileNotFoundException {
        pdfGenerator.generateCurriculumPDF(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

}

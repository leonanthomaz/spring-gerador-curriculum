package app.vercel.leonanthomaz.springcurriculum.controller;

import app.vercel.leonanthomaz.springcurriculum.component.PDFGenerator;
import app.vercel.leonanthomaz.springcurriculum.model.Usuario;
import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurriculumControllerTest {

    @Mock
    private PDFGenerator pdfGenerator;

    private CurriculumController curriculumController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        curriculumController = new CurriculumController(pdfGenerator);
    }

    @Test
    void generateCurriculumPDF_WhenObjectUsuarioValid_ReturnSuccess() throws DocumentException, FileNotFoundException {
        // Mock data
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@example.com");

        // Mock do PDFGenerator para verificar se o método generateCurriculumPDF é chamado corretamente
        doNothing().when(pdfGenerator).generateCurriculumPDF(usuario);

        // Chamada do método a ser testado
        ResponseEntity<Usuario> responseEntity = curriculumController.gerarPDFCurriculo(usuario);

        // Verificação do resultado
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());

        // Verifica se o método do PDFGenerator foi chamado exatamente uma vez
        verify(pdfGenerator, times(1)).generateCurriculumPDF(usuario);
    }
}
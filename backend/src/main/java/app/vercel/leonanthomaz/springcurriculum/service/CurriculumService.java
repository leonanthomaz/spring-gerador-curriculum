package app.vercel.leonanthomaz.springcurriculum.service;

import app.vercel.leonanthomaz.springcurriculum.component.PDFGenerator;
import app.vercel.leonanthomaz.springcurriculum.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por gerar currículos em PDF.
 */
@Service
public class CurriculumService {

    @Autowired
    private PDFGenerator pdfGenerator;

    /**
     * Gera um currículo em PDF com base nos dados do usuário fornecidos.
     *
     * @param usuario Objeto Usuario contendo os dados para o currículo.
     * @throws Exception se ocorrer um erro ao gerar o currículo.
     */
    public void generateCurriculum(Usuario usuario) throws Exception {
        pdfGenerator.generateCurriculumPDF(usuario);
    }
}
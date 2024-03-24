package app.vercel.leonanthomaz.springcurriculum.component;

import app.vercel.leonanthomaz.springcurriculum.model.Experiencia;
import app.vercel.leonanthomaz.springcurriculum.model.Usuario;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Componente responsável por gerar currículos em formato PDF.
 */
@Component
public class PDFGenerator {

    private final Path basePath;
    private int fileCounter = 1;

    public PDFGenerator(@Value("${curriculum.storage.path}") String storagePath) {
        this.basePath = Paths.get(storagePath);
        initializeFileCounter();
    }

    /**
     * Gera um currículo em PDF com base nos dados do usuário fornecidos.
     *
     * @param usuario Objeto Usuario contendo os dados para o currículo.
     * @throws DocumentException    se houver erro ao manipular o documento PDF.
     * @throws FileNotFoundException se o arquivo PDF não for encontrado.
     */

    public void generateCurriculumPDF(Usuario usuario) throws FileNotFoundException, DocumentException {
        String path = basePath.resolve("curriculo" + fileCounter + ".pdf").toString();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();

        addPersonalInfoToDocument(usuario, document);
        addProfessionalExperiencesToDocument(usuario.getExperiencias(), document);

        document.close();
    }

    /**
     * Adiciona as informações pessoais do usuário ao documento PDF.
     *
     * @param usuario  Objeto Usuario contendo as informações pessoais.
     * @param document Document onde as informações serão adicionadas.
     * @throws DocumentException se houver erro ao manipular o documento PDF.
     */

    private void addPersonalInfoToDocument(Usuario usuario, Document document) throws DocumentException {
        Font titleFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        Paragraph personalInfoTitle = new Paragraph("Dados Pessoais", titleFontBold);
        document.add(personalInfoTitle);

        LocalDate dataCadastroFixa = LocalDate.of(2023, 5, 19); // Criação da data fixa
        String dataCadastroFormatada = dataCadastroFixa.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Formata a data para string

        document.add(new Chunk("Nome: ", titleFontBold));
        document.add(new Chunk(usuario.getNome(), regularFont));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("Email: ", titleFontBold));
        document.add(new Chunk(usuario.getEmail(), regularFont));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("Telefone: ", titleFontBold));
        document.add(new Chunk(usuario.getTelefone(), regularFont));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("Data de Nascimento: ", titleFontBold));
        document.add(new Chunk(String.valueOf(usuario.getDataNascimento()), regularFont));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("Gênero: ", titleFontBold));
        document.add(new Chunk(usuario.getGenero(), regularFont));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("Data do Cadastro: ", titleFontBold));
        document.add(new Chunk(dataCadastroFormatada, regularFont));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("Último acesso: ", titleFontBold));
        document.add(new Chunk(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), regularFont));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
    }

    /**
     * Adiciona as experiências profissionais do usuário ao documento PDF.
     *
     * @param experiencias Lista de Experiencia contendo as experiências profissionais.
     * @param document     Document onde as experiências serão adicionadas.
     * @throws DocumentException se houver erro ao manipular o documento PDF.
     */
    private void addProfessionalExperiencesToDocument(List<Experiencia> experiencias, Document document) throws DocumentException {
        Font titleFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        Paragraph experienceInfoTitle = new Paragraph("Experiências Profissionais", titleFontBold);
        document.add(experienceInfoTitle);

        for (Experiencia experiencia : experiencias) {
            document.add(new Chunk("Cargo: ", titleFontBold));
            document.add(new Chunk(experiencia.getCargo(), regularFont));
            document.add(Chunk.NEWLINE);

            document.add(new Chunk("Empresa: ", titleFontBold));
            document.add(new Chunk(experiencia.getEmpresa(), regularFont));
            document.add(Chunk.NEWLINE);

            document.add(new Chunk("Data de Início: ", titleFontBold));
            document.add(new Chunk(experiencia.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), regularFont));
            document.add(Chunk.NEWLINE);

            document.add(new Chunk("Data de Término: ", titleFontBold));
            document.add(new Chunk(experiencia.getDataTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), regularFont));
            document.add(Chunk.NEWLINE);

            document.add(new Chunk("Descrição: ", titleFontBold));
            document.add(new Chunk(experiencia.getDescricao(), regularFont));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        }
    }

    /**
     * Incrementa o contador de arquivos para garantir nomes únicos de currículos.
     */
    private void initializeFileCounter() {
        File directory = basePath.toFile();
        File[] files = directory.listFiles();
        if (files != null) {
            // Conta o número de arquivos de currículo no diretório e define fileCounter para o próximo número
            fileCounter = files.length + 1;
        }
    }
}
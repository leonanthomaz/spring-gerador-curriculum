package app.vercel.leonanthomaz.springcurriculum.component;

import app.vercel.leonanthomaz.springcurriculum.model.Experiencia;
import app.vercel.leonanthomaz.springcurriculum.model.Usuario;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/***
 * Coração do projeto. O PDFGenerator recebe o usuario e monta o curriculum com as informações passadas.
 * O path escolhido aponta para a pasta "storage", onde atraves da biblioteca iText transforma coletados em informações para o
 * preenchimento do PDF.
 */
@Component
public class PDFGenerator {
    private static final String FILE_PATH = "C:\\dev\\projects\\java\\springcurriculum\\src\\main\\java\\app\\vercel\\leonanthomaz\\springcurriculum\\storage\\curriculo";
    private int fileCounter = 1;
    public void generateCurriculumPDF(Usuario usuario) throws FileNotFoundException, DocumentException {
        String path = FILE_PATH + fileCounter + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();

//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(path+1));
//        document.open();

        Font titleFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
        Font titleFontUnderline = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.UNDERLINE, BaseColor.BLACK);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        // Dados Pessoais
        Paragraph personalInfoTitle = new Paragraph("Dados Pessoais", titleFontUnderline);
        document.add(personalInfoTitle);

        // Data do Cadastro
        LocalDate dataCadastroFixa = LocalDate.of(2023, 5, 19); // Criação da data fixa
        String dataCadastroFormatada = dataCadastroFixa.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Formata a data para strin

        // Adiciona dados pessoais
//        String nome = usuario.getNome() != null ? usuario.getNome() : ""; // Verifica se o nome do usuário é nulo
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

        // Experiências Profissionais
        Paragraph experienceInfoTitle = new Paragraph("Experiências Profissionais", titleFontUnderline);
        document.add(experienceInfoTitle);

        // Adiciona experiências profissionais
        addExperienciasToDocument(usuario.getExperiencias(), document, titleFontBold, regularFont);

        // Fecha o documento
        document.close();
        incrementFileCounter();
    }

    private void addExperienciasToDocument(List<Experiencia> experiencias, Document document, Font titleFontBold, Font regularFont) throws DocumentException {
        for (Experiencia exp : experiencias) {
            document.add(new Chunk("Cargo: ", titleFontBold));
            document.add(new Chunk(exp.getCargo(), regularFont));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("Empresa: ", titleFontBold));
            document.add(new Chunk(exp.getEmpresa(), regularFont));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("Data de Início: ", titleFontBold));
            document.add(new Chunk(String.valueOf(exp.getDataInicio()), regularFont));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("Data de Término: ", titleFontBold));
            document.add(new Chunk(String.valueOf(exp.getDataTermino()), regularFont));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("Descrição: ", titleFontBold));
            document.add(new Chunk(exp.getDescricao(), regularFont));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        }
    }
    private void incrementFileCounter() {
        fileCounter++;
    }
}

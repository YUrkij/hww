package hw5;

import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн команда
 */
interface Command {
    void execute();
}
/**
 * Паттерн посетитель
 */
interface DocumentVisitor {
    void visit(TextFile textFile);
    void visit(ImageFile imageFile);
}

/**
 * Базовый класс документа
 */
abstract class Document {
    private final String name;

    public Document(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void accept(DocumentVisitor visitor);

    public abstract void open();
    public abstract void save();
}

class TextFile extends Document {
    public TextFile(String name) {
        super(name);
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void open() {
        System.out.println("Открытие текстового файла: " + getName());
    }

    @Override
    public void save() {
        System.out.println("Сохранение текстового файла: " + getName());
    }
}

class ImageFile extends Document {
    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void open() {
        System.out.println("Просмотр изображения: " + getName());
    }

    @Override
    public void save() {
        System.out.println("Экспорт изображения: " + getName());
    }
}

class OpenCommand implements Command {
    private final Document document;

    public OpenCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.open();
    }
}

class SaveCommand implements Command {
    private final Document document;

    public SaveCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.save();
    }
}

class StatsVisitor implements DocumentVisitor {
    private int textCount = 0;
    private int imageCount = 0;

    @Override
    public void visit(TextFile textFile) {
        textCount++;
    }

    @Override
    public void visit(ImageFile imageFile) {
        imageCount++;
    }

    public void printStats() {
        System.out.println("Статистика документов:");
        System.out.println("Текстовые файлы: " + textCount);
        System.out.println("Изображения: " + imageCount);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Document> documents = new ArrayList<>();
        documents.add(new TextFile("report.txt"));
        documents.add(new ImageFile("photo.jpg"));
        documents.add(new TextFile("notes.txt"));

        Command openReport = new OpenCommand(documents.get(0));
        Command savePhoto = new SaveCommand(documents.get(1));

        openReport.execute();  // "Открытие" текстового файла: report.txt
        savePhoto.execute();   // "Экспорт" изображения: photo.jpg

        StatsVisitor statsVisitor = new StatsVisitor();
        for (Document doc : documents) {
            doc.accept(statsVisitor);
        }
        statsVisitor.printStats();
    }
}
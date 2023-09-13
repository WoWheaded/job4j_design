package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainMain {
    public static void main(String[] args) throws Exception {
        Worker worker = new Worker(true, 200_000, "Dmitry",
                new WorkersProject("Pet clinic", 1), new String[]{"HR", "IT"});
        JAXBContext context = JAXBContext.newInstance(Worker.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(worker, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Worker result = (Worker) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

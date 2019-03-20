package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LXMLEncoding {

    public Map<String, Integer> mapping;

    public LXMLEncoding(Map<String, Integer> mapping) {
        this.mapping = mapping;
    }

    public static void main(String[] args) {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("family", 1);
        mapping.put("person", 2);
        mapping.put("firstName", 3);
        mapping.put("lastName", 4);
        mapping.put("state", 5);
        LXMLEncoding encoding = new LXMLEncoding(mapping);

        Tag familyTag = new Tag("family");
        Element family = new Element(familyTag);
        Tag lastNameTag = new Tag("lastName");
        Attribute lastNameAttribute = new Attribute(lastNameTag, "McDowell");
        Tag stateTag = new Tag("state");
        Attribute stateAttribute = new Attribute(stateTag, "CA");

        Tag personTag = new Tag("person");
        Element person = new Element(personTag);
        Tag firstNameTag = new Tag("firstName");
        Attribute firstNameAttribute = new Attribute(firstNameTag, "Gayle");
        person.getAttributes().add(firstNameAttribute);
        person.setValue("Some Message");

        family.getAttributes().add(lastNameAttribute);
        family.getAttributes().add(stateAttribute);
        family.getChildren().add(person);
        encoding.encodeThis(family);
    }

    private void encodeThis(Element XMLInput) {
        StringBuilder stringBuilder = new StringBuilder();
        processElement(XMLInput, stringBuilder);
        System.out.println("Encoded String: ");
        System.out.println(stringBuilder.toString());
    }

    private void processElement(Element element, StringBuilder stringBuilder) {
        stringBuilder.append(this.mapping.get(element.getTag().getName())).append(" ");
        List<Attribute> attributes = element.getAttributes();
        for (Attribute attribute : attributes) {
            processAttribute(attribute, stringBuilder);
        }

        stringBuilder.append("0").append(" ");//END after attribute

        List<Element> children = element.getChildren();
        for (Element child : children) {
            processElement(child, stringBuilder);
        }
        if (element.getValue() != null) {
            stringBuilder.append(element.getValue()).append(" ");
        }
        stringBuilder.append("0").append(" ");//END after the children or value
    }

    private void processAttribute(Attribute attribute, StringBuilder stringBuilder) {
        stringBuilder.append(this.mapping.get(attribute.getTag().getName())).append(" ");
        stringBuilder.append(attribute.getValue()).append(" ");
    }
}

class Element {
    private Tag tag;
    private List<Attribute> attributes;
    private String value;
    private List<Element> children;

    public Element(Tag tag) {
        this.tag = tag;
        this.attributes = new ArrayList<>();
        this.value = null;
        this.children = new ArrayList<>();
    }

    public Tag getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Element> getChildren() {
        return children;
    }
}

class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Attribute {
    private Tag tag;
    private String value;

    public Attribute(Tag tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public Tag getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
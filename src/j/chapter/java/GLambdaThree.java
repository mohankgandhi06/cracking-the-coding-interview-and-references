package j.chapter.java;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class GLambdaThree {
    public static void main(String[] args) {
        /*  Consumer  */

        Consumer<MessageBuilder> consumer = (message) -> {
            System.out.println("From: " + message.getFromEmailId());
            System.out.println("To: " + message.getToEmailId());
            System.out.println("Hi " + message.getToEmailId().substring(0, message.getFromEmailId().indexOf("@") + 2) + ",");
            System.out.println(message.getMessage());
            System.out.println(message.getPostScript());
            System.out.println("With Regards,\n" + message.getFromEmailId().substring(0, message.getFromEmailId().indexOf("@")));
        };

        System.out.println("Message's Today:");
        /*  Supplier  */
        Supplier<MessageBuilder> supplier = () -> {
            return new MessageBuilder("mohan@gmail.com", "bharath@gmail.com", "Switch off the lights and sleep.", "Good Night");
        };
        consumer.accept(supplier.get());

        /*  Function  */
        Function<MessageBuilder, String> getMessageString = (messageBuilder) -> {
            return messageBuilder.getMessage() + "\n" + messageBuilder.getPostScript();
        };

        System.out.println();
        System.out.println("Message Alone: \n" + getMessageString.apply(supplier.get()));

        /* Steps to create the lambda instance
           Parameter One: (Interface Parameters)
           Parameter Two: Arrow Token ->
           Parameter Three: function (Implementation of the Interface's Functional Interface method)
                            to be performed within the curly braces {} */

        /*  Function Chaining  */
        Function<MessageBuilder, String> getSenderAndReceiver = (messageBuilder) -> {
            System.out.println(messageBuilder.getFromEmailId().substring(0, messageBuilder.getFromEmailId().indexOf('@'))
                    + " "
                    + messageBuilder.getToEmailId().substring(0, messageBuilder.getToEmailId().indexOf('@')));
            return messageBuilder.getFromEmailId().substring(0, messageBuilder.getFromEmailId().indexOf('@'))
                    + " "
                    + messageBuilder.getToEmailId().substring(0, messageBuilder.getToEmailId().indexOf('@'));
        };

        Function<String, String> getReceiverInfoAlone = (senderAndReceiver) -> {
            return senderAndReceiver.substring(senderAndReceiver.indexOf(' ') + 1);
        };

        Function<MessageBuilder, String> chainingFunctions = getSenderAndReceiver.andThen(getReceiverInfoAlone);

        System.out.println();
        System.out.println("Chaining Function:");
        System.out.println(chainingFunctions.apply(supplier.get()));

        /*  Stream  */
        AttendanceList attendanceList = new AttendanceList(6, "6A");
        attendanceList.setStudentsList(Arrays.asList(
                "Damon", "Matt", "John", "Steve", "Pollock", "Mark", "Emily", "Wanda", "Susan", "Mendy"
        ));

        long count = attendanceList.getStudentsList().stream().map(UpperCaseHelper::toUpperCase).filter(s -> s.startsWith("S")).peek(System.out::println).count();

        System.out.println("Attendance for People Starting with S: " + count);
    }
}

class UpperCaseHelper {
    public static String toUpperCase(String string) {
        return string.toUpperCase();
    }
}

class MessageBuilder {
    private String fromEmailId;
    private String toEmailId;
    private String message;
    private String postScript;

    public MessageBuilder(String fromEmailId, String toEmailId, String message, String postScript) {
        this.fromEmailId = fromEmailId;
        this.toEmailId = toEmailId;
        this.message = message;
        this.postScript = postScript;
    }

    public String getFromEmailId() {
        return fromEmailId;
    }

    public void setFromEmailId(String fromEmailId) {
        this.fromEmailId = fromEmailId;
    }

    public String getToEmailId() {
        return toEmailId;
    }

    public void setToEmailId(String toEmailId) {
        this.toEmailId = toEmailId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostScript() {
        return postScript;
    }

    public void setPostScript(String postScript) {
        this.postScript = postScript;
    }
}

class AttendanceList {
    private int classId;
    private String className;
    private List<String> studentsList;
    private int presentCount;

    public AttendanceList(int classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<String> studentsList) {
        this.studentsList = studentsList;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }
}
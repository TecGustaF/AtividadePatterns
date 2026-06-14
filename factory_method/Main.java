interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("EmailFactory enviando: \"" + message + "\"");
    }
}

class SMSNotification implements Notification {
    public void send(String message) {
        System.out.println("SMSFactory enviando: \"" + message + "\"");
    }
}

class PushNotification implements Notification {
    public void send(String message) {
        System.out.println("PushFactory enviando: \"" + message + "\"");
    }
}

abstract class NotificationFactory {
    public abstract Notification createNotification();
}

class EmailNotificationFactory extends NotificationFactory {
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class SMSNotificationFactory extends NotificationFactory {
    public Notification createNotification() {
        return new SMSNotification();
    }
}

class PushNotificationFactory extends NotificationFactory {
    public Notification createNotification() {
        return new PushNotification();
    }
}

class NotificationService {
    private NotificationFactory factory;

    public NotificationService(NotificationFactory factory) {
        this.factory = factory;
    }

    public void notify(String message) {
        Notification notification = factory.createNotification();
        notification.send(message);
    }
}

public class Main {
    public static void main(String[] args) {
        String message = "Bem-vindo ao sistema!";

        NotificationService emailService = new NotificationService(new EmailNotificationFactory());
        emailService.notify(message);

        NotificationService smsService = new NotificationService(new SMSNotificationFactory());
        smsService.notify(message);

        NotificationService pushService = new NotificationService(new PushNotificationFactory());
        pushService.notify(message);
    }
}

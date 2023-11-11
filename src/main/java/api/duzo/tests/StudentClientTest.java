package api.duzo.tests;

import api.duzo.client.StudentClient;

public class StudentClientTest {
    public static void main(String[] args) {
        StudentClient client = new StudentClient("TCPRG9XTYV","07/09/2007");
        try {
            client.login();

            System.out.println(client.getStudentCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
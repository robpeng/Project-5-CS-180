import java.util.*;
import java.io.*;
class Record {
    private String username;
    private String password;
    public Record(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
public class LoginServer {
    String usernameField = null;
    static ArrayList<Record> records = new ArrayList<Record>();
    File f = new File("records.txt");
    private static Object gateKeeper = new Object();

    PrintWriter netpw;
    Scanner netscan;

    public void setNetScanPw(Scanner netscan, PrintWriter netpw) {
        this.netscan = netscan;
        this.netpw = netpw;
    }

    public LoginServer() {

        synchronized (gateKeeper) {
            if (f.exists() && records.size() == 0) {
                FileReader fr;
                try {
                    fr = new FileReader(f);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                BufferedReader br = new BufferedReader(fr);
                String str;
                String[] arr = new String[2];
                while (true) {
                    try {
                        str = br.readLine();
                        if (str == null) {
                            break;
                        }
                        arr = str.split(",");
                        Record record = new Record(arr[0], arr[1]);
                        synchronized (gateKeeper) {
                            records.add(record);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
    }
    public boolean addUsername(String username) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getUsername().equals(username)) {
                netpw.write("032~ The file was not found.\n");
                netpw.flush();
                return false;
            }
        }
        //.setUsername(username);
        usernameField = username;
        netpw.write("030~ Success addusername()\n");
        netpw.flush();
        return true;
    }
    public boolean addPassword(String password) {
        if (usernameField == null) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return false;
        }
        Record record = new Record(usernameField, password);
        //record.setPassword(password);
        //System.out.println(record.getUsername() + ", " + record.getPassword() + " before add");

        usernameField = null;
        /*
        for (Record each: records) {
            System.out.println(each.getUsername() + ", " + each.getPassword() + " our stuff");
        }
        */

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f, true);
        } catch(IOException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return false;
        }
        PrintWriter pw = new PrintWriter(fos);
        synchronized (gateKeeper) {
            records.add(record);
            pw.println(record.getUsername() + "," + record.getPassword());
            pw.close();
        }
        netpw.write("030~ Success addpassword()\n");
        netpw.flush();
        return true;
    }
    public boolean alreadyUser(String username, String password) {
        for (Record each: records) {
            if (each.getUsername().equals(username) && each.getPassword().equals(password)) {
                System.out.println("Login success!");
                netpw.write("030~ Success alreadyUser()\n");
                netpw.flush();
                return true;
            }
        }
        System.out.println("Username or password is wrong.");
        netpw.write("032~ The file was not found.\n");
        netpw.flush();
        return false;
    }

    public boolean deleteUser(String username, String password) {
        boolean flag = false;
        int index = 0;
        synchronized (gateKeeper) {
            for (Record each : records) {
                if (each.getUsername().equals(username) && each.getPassword().equals(password)) {
                    flag = true;
                    break;
                }
                index++;
            }
            if (flag) {
                records.remove(index);
                writeRecordsToFile();
                netpw.write("030~ Success deleteUser()\n");
                netpw.flush();
                return true;
            } else {
                netpw.write("032~ The file was not found.\n");
                netpw.flush();
                return false;
            }
        }
    }
    public boolean existingUser(String username) {
        for (Record each: records) {
            if (each.getUsername().equals(username)) {
                netpw.write("030~ Success existinguser() \n");
                netpw.flush();
                return true;
            }
        }
        netpw.write("032~ The file was not found.\n");
        netpw.flush();
        return false;
    }
    public boolean editUsername(String username, String newUsername) {
        ///System.out.println(username + " : " + newUsername + "this is our notification.");
        boolean existinguserflag = false;
        for (Record each: records) {
            if (each.getUsername().equals(username)) {
                existinguserflag = true;
                break;
            }
        }
        for (Record each : records) {
            if (each.getUsername().equals(username)) {

                if (existinguserflag) {
                    System.out.println("Username already exists!");
                    netpw.write("032~ The username already exists.\n");
                    netpw.flush();
                    return false;
                } else {
                    synchronized (gateKeeper) {
                        each.setUsername(newUsername);
                        writeRecordsToFile();
                    }
                    System.out.println("Editing was success.");
                    netpw.write("030~ Success editusername()\n");
                    netpw.flush();
                    return true;
                }
            }
        }
        netpw.write("032~ The name was not found.\n");
        netpw.flush();
        return false;
    }
    public void editPassword(String username, String newPassword) {
        for (Record each : records) {
            if (each.getUsername().equals(username)) {
                synchronized (gateKeeper) {
                    each.setPassword(newPassword);
                    writeRecordsToFile();
                }
                System.out.println("Success!");
                netpw.write("030~ Success editpasswrod()\n");
                netpw.flush();
                return;
            }
        }
        netpw.write("032~ The file was not found.\n");
        netpw.flush();
    }


    private void writeRecordsToFile() {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f, false);
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }
        PrintWriter pw = new PrintWriter(fos);
        synchronized (gateKeeper) {
            for (Record each : records) {
                pw.println(each.getUsername() + "," + each.getPassword());
            }
            pw.close();
        }
    }

}



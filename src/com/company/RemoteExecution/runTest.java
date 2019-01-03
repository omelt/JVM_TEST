package com.company.RemoteExecution;

public class runTest {
    public static void main(String[] args) {
        System.out.println("我也不知道有啥用");
    }
}


    /*@RequestMapping(value = "/test" ,method = RequestMethod.POST)
    public void runJava(@RequestParam String tar) throws IOException {
        InputStream is = new FileInputStream(tar);
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();

        System.out.print(JavaClassExecuter.execute(b));
    }*/
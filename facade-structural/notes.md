take a look at this code:

```java
class CPU {
    public void freeze() {
        System.out.println("CPU freezing...");
    }

    public void jump(long position) {
        System.out.println("CPU jumping to position " + position + "...");
    }

    public void execute() {
        System.out.println("CPU executing...");
    }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Loading data into memory at position " + position + "...");
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("Reading " + size + " bytes from hard drive at LBA " + lba + "...");
        return new byte[size];
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        // Client interacts directly with the subsystem classes
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        // Client needs to know the sequence of operations and the methods to call
        cpu.freeze();
        byte[] data = hardDrive.read(0, 1024);
        memory.load(0, data);
        cpu.jump(0);
        cpu.execute();
    }
}
```

Notice that the client interacts direclty with the classes. The facade pattern will allow you do to interact
with a **unified** class by delegating the method calls to the appropriate class

```java

class CPU {
    public void freeze() {
        System.out.println("CPU freezing...");
    }

    public void jump(long position) {
        System.out.println("CPU jumping to position " + position + "...");
    }

    public void execute() {
        System.out.println("CPU executing...");
    }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Loading data into memory at position " + position + "...");
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("Reading " + size + " bytes from hard drive at LBA " + lba + "...");
        return new byte[size];
    }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}

public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
```

Now the client just call the start method and facade will handle the rest of it

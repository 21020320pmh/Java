package Study;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyQueue<T> implements BlockingQueue<T> {
    private final T[] queue;
    private int head;
    private int tail;
    private int size;
    private final int capacity;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    @Override
    public synchronized boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (queue[(head + i) % capacity].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized int drainTo(@NotNull Collection<? super T> c) {
        int count = 0;
        while (size > 0) {
            c.add(queue[head]);
            head = (head + 1) % capacity;
            size--;
            count++;
        }
        return count;
    }

    @Override
    public synchronized int drainTo(@NotNull Collection<? super T> c, int maxElements) {
        int count = 0;
        while (size > 0 && count < maxElements) {
            c.add(queue[head]);
            head = (head + 1) % capacity;
            size--;
            count++;
        }
        return count;
    }

    @Override
    public synchronized @NotNull Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = head;
            private int elementsLeft = size;

            @Override
            public boolean hasNext() {
                return elementsLeft > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = queue[currentIndex];
                currentIndex = (currentIndex + 1) % capacity;
                elementsLeft--;
                return value;
            }
        };
    }

    @Override
    public synchronized Object @NotNull [] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = queue[(head + i) % capacity];
        }
        return array;
    }

    @Override
    public synchronized <T> T @NotNull [] toArray(T @NotNull [] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        for (int i = 0; i < size; i++) {
            a[i] = (T) queue[(head + i) % capacity];
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public synchronized boolean add(@NotNull T value) {
        if (size >= capacity) {
            throw new IllegalStateException("Queue full");
        }
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
        return true;
    }

    @Override
    public synchronized boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            int index = (head + i) % capacity;
            if (queue[index].equals(o)) {
                for (int j = i; j < size - 1; j++) {
                    queue[(head + j) % capacity] = queue[(head + j + 1) % capacity];
                }
                tail = (tail - 1 + capacity) % capacity;
                size--;
                notifyAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean containsAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public synchronized boolean addAll(@NotNull Collection<? extends T> c) {
        if (size + c.size() > capacity) {
            throw new IllegalStateException("Queue full");
        }
        for (T value : c) {
            add(value);
        }
        return true;
    }

    @Override
    public synchronized boolean removeAll(@NotNull Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public synchronized boolean retainAll(@NotNull Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            int index = (head + i) % capacity;
            if (!c.contains(queue[index])) {
                remove(queue[index]);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public synchronized void clear() {
        head = 0;
        tail = 0;
        size = 0;
        notifyAll();
    }

    @Override
    public synchronized boolean offer(@NotNull T value) {
        if (size >= capacity) {
            return false;
        }
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
        return true;
    }

    @Override
    public synchronized void put(@NotNull T value) throws InterruptedException {
        while (size >= capacity) {
            wait();
        }
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
    }

    @Override
    public synchronized boolean offer(T value, long timeout, @NotNull TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        long deadline = System.nanoTime() + nanos;
        while (size >= capacity) {
            if (nanos <= 0L) {
                return false;
            }
            wait(nanos / 1000000L, (int) (nanos % 1000000L));
            nanos = deadline - System.nanoTime();
        }
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
        return true;
    }

    @Override
    public synchronized @NotNull T take() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        T value = queue[head];
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return value;
    }

    @Override
    public synchronized @Nullable T poll(long timeout, @NotNull TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        long deadline = System.nanoTime() + nanos;
        while (size == 0) {
            if (nanos <= 0L) {
                return null;
            }
            wait(nanos / 1000000L, (int) (nanos % 1000000L));
            nanos = deadline - System.nanoTime();
        }
        T value = queue[head];
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return value;
    }

    @Override
    public synchronized int remainingCapacity() {
        return capacity - size;
    }

    @Override
    public synchronized T remove() {
        if (size == 0) {
            throw new IllegalStateException("Queue empty");
        }
        T value = queue[head];
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return value;
    }

    @Override
    public synchronized T poll() {
        if (size == 0) {
            return null;
        }
        T value = queue[head];
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return value;
    }

    @Override
    public synchronized T element() {
        if (size == 0) {
            throw new IllegalStateException("Queue empty");
        }
        return queue[head];
    }

    @Override
    public synchronized T peek() {
        if (size == 0) {
            return null;
        }
        return queue[head];
    }
}
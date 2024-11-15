import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int HISTORY_LIMIT = 10;
    private final List<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (history.size() >= HISTORY_LIMIT) {
            history.removeFirst();
        }
        history.add(new Task(task));
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}

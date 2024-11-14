import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    void testAddTaskToHistory() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        Task task = new Task("Задача", "Описание", TaskStatus.NEW);

        historyManager.add(task);

        List<Task> history = historyManager.getHistory();

        assertNotNull(history, "История не должна быть null");
        assertEquals(1, history.size(), "Размер истории должен быть 1");
        assertEquals(task, history.get(0), "Задача в истории должна соответствовать добавленной задаче.");
    }

    @Test
    void testPreserveTaskStateInHistory() {
        HistoryManager historyManager = new InMemoryHistoryManager();

        Task task = new Task("Задача", "Описание", TaskStatus.NEW);
        historyManager.add(task);

        task.setName("Измененное имя");
        task.setDescription("Измененное описание");

        List<Task> history = historyManager.getHistory();
        assertEquals("Задача", history.getFirst().getName(),
                "Имя задачи в истории не должно меняться после изменения исходного объекта");
        assertEquals("Описание", history.getFirst().getDescription(),
                "Описание задачи в истории не должно меняться после изменения исходного объекта");
    }
}

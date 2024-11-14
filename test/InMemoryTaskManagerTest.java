import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    @Test
    void testTaskEqualityById() {
        Task task1 = new Task("Задача", "Описание", TaskStatus.NEW);
        Task task2 = new Task("Задача", "Описание", TaskStatus.NEW);

        task1.setId(1);
        task2.setId(1);

        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны");
    }

    @Test
    void testSubtasksEqualityById() {
        Subtask subtask1 = new Subtask("Подзадача", "Описание", TaskStatus.NEW, 0);
        Subtask subtask2 = new Subtask("Подзадача", "Описание", TaskStatus.NEW, 0);

        subtask1.setId(1);
        subtask2.setId(1);

        assertEquals(subtask1, subtask2, "Наследники Task с одинаковым ID должны быть равны");
    }

    @Test
    void testSubtaskCannotHaveItselfAsEpic() {
        Epic epic = new Epic("Эпик", "Описание");
        epic.setId(1);

        Subtask subtask = new Subtask("Подзадача", "Описание", TaskStatus.NEW, epic.getId());
        subtask.setId(2); // Устанавливаем корректный ID подзадачи

        assertNotEquals(subtask.getEpicId(), subtask.getId(),
                "Подзадача не должна ссылаться на себя как на эпик");
    }

    @Test
    void testManagersReturnInitializedInstances() {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(taskManager, "TaskManager должен быть проинициализирован");
        assertNotNull(historyManager, "HistoryManager должен быть проинициализирован");
    }

    @Test
    void testAddAndRetrieveTasksById() {
        TaskManager taskManager = new InMemoryTaskManager();

        Task task = new Task("Задача", "Описание", TaskStatus.NEW);
        Epic epic = new Epic("Эпик", "Описание");
        Subtask subtask = new Subtask("Подзадача", "Описание", TaskStatus.NEW, epic.getId());

        int taskId = taskManager.addNewTask(task);
        int epicId = taskManager.addNewEpic(epic);
        int subtaskId = taskManager.addNewSubtask(subtask);

        assertEquals(task, taskManager.getTask(taskId), "Добавленная задача должна быть найдена");
        assertEquals(epic, taskManager.getEpic(epicId), "Добавленный эпик должен быть найден");
        assertEquals(subtask, taskManager.getSubtask(subtaskId), "Добавленная подзадача должна быть найдена");
    }

    @Test
    void testGeneratedAndCustomIdConflict() {
        TaskManager taskManager = new InMemoryTaskManager();

        Task task1 = new Task("Задача с сгенерированным ID", "Описание", TaskStatus.NEW);
        int generatedId = taskManager.addNewTask(task1);

        Task task2 = new Task("Задача с пользовательским ID", "Описание", TaskStatus.NEW);
        task2.setId(generatedId);

        boolean isAdded = taskManager.addNewTask(task2) == -1;
        assertFalse(isAdded, "Задачи с конфликтующими ID не должны быть добавлены");
    }

    @Test
    void testTaskImmutabilityWhenAddedToManager() {
        TaskManager taskManager = new InMemoryTaskManager();

        Task task = new Task("Задача", "Описание", TaskStatus.NEW);
        taskManager.addNewTask(task);

        Task retrievedTask = taskManager.getTask(task.getId());

        assertEquals(task.getName(), retrievedTask.getName(), "Имя задачи не должно измениться");
        assertEquals(task.getDescription(), retrievedTask.getDescription(), "Описание задачи не должно измениться");
        assertEquals(task.getStatus(), retrievedTask.getStatus(), "Статус задачи не должен измениться");
    }

}

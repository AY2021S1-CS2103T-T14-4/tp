package tp.acecs2103.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tp.acecs2103.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tp.acecs2103.commons.exceptions.IllegalValueException;
import tp.acecs2103.commons.util.JsonUtil;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.testutil.TypicalTasks;

public class JsonSerializableTaskListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableTaskListTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTaskList.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskList.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTaskList.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTaskList.class).get();
        TaskList taskListFromFile = dataFromFile.toModelType();
        TaskList typicalTasksTaskList = TypicalTasks.getTypicalTaskList();
        assertEquals(taskListFromFile, typicalTasksTaskList);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}


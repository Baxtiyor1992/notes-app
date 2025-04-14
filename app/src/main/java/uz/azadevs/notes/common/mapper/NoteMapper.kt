package uz.azadevs.notes.common.mapper

import uz.azadevs.notes.common.utils.DateUtils
import uz.azadevs.notes.data.local.entity.NoteEntity
import uz.azadevs.notes.data.local.entity.TopicEntity
import uz.azadevs.notes.data.local.entity.TopicWithNoteCountEntity
import uz.azadevs.notes.data.local.entity.TopicWithNotesEntity
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.model.TopicWithNoteCount
import uz.azadevs.notes.domain.model.TopicWithNotes

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        description = description,
        topic = topic,
        date = DateUtils.convertLongToTime(createdAt),
        topicId = topicId
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description,
        topic = topic,
        createdAt = DateUtils.convertDateToLong(date),
        topicId = topicId
    )
}

fun TopicEntity.toTopic(): Topic {
    return Topic(
        id = id,
        name = name,
        icon = icon
    )
}

fun Topic.toEntity(): TopicEntity {
    return TopicEntity(
        id = id,
        name = name,
        icon = icon
    )
}

fun TopicWithNotesEntity.toTopicWithNotes(): TopicWithNotes {
    return TopicWithNotes(
        topic = topic.toTopic(),
        notes = notes.toNotes()
    )
}

fun TopicWithNoteCountEntity.toTopicWithNoteCount(): TopicWithNoteCount {
    return TopicWithNoteCount(
        id = id,
        title = title,
        icon = icon,
        noteCount = noteCount
    )
}

fun List<TopicWithNoteCountEntity>.toTopicWithNoteCounts(): List<TopicWithNoteCount> {
    return map {
        it.toTopicWithNoteCount()
    }
}

fun List<NoteEntity>.toNotes(): List<Note> {
    return map {
        it.toNote()
    }
}

fun List<TopicEntity>.toTopics(): List<Topic> {
    return map {
        it.toTopic()
    }
}

fun List<TopicWithNotesEntity>.toTopicWithNotes(): List<TopicWithNotes> {
    return map {
        it.toTopicWithNotes()
    }
}

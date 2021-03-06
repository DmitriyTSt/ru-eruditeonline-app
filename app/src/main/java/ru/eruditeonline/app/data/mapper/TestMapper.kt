package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.Answer
import ru.eruditeonline.app.data.model.test.CompetitionTest
import ru.eruditeonline.app.data.model.test.CreatedResult
import ru.eruditeonline.app.data.model.test.Question
import ru.eruditeonline.app.data.model.test.ResultAnswer
import ru.eruditeonline.app.data.model.test.ResultInfo
import ru.eruditeonline.app.data.model.test.TempResult
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.data.model.test.TestUserResult
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.remote.model.test.ApiAnswer
import ru.eruditeonline.app.data.remote.model.test.ApiCompetitionTest
import ru.eruditeonline.app.data.remote.model.test.ApiCreatedResult
import ru.eruditeonline.app.data.remote.model.test.ApiQuestion
import ru.eruditeonline.app.data.remote.model.test.ApiQuestionType
import ru.eruditeonline.app.data.remote.model.test.ApiResultAnswer
import ru.eruditeonline.app.data.remote.model.test.ApiResultInfo
import ru.eruditeonline.app.data.remote.model.test.ApiTempResult
import ru.eruditeonline.app.data.remote.model.test.ApiTestCommonResultRow
import ru.eruditeonline.app.data.remote.model.test.ApiTestUserResult
import ru.eruditeonline.app.data.remote.model.test.ApiTestUserResultRow
import ru.eruditeonline.app.data.remote.params.CompetitionCheckParams
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class TestMapper @Inject constructor(
    private val baseMapper: BaseMapper,
) {
    fun fromApiToModel(api: ApiTestCommonResultRow): TestCommonResultRow {
        return TestCommonResultRow(
            date = Instant.ofEpochSecond(api.date.orDefault()).atZone(ZoneId.systemDefault()).toLocalDate(),
            username = api.username.orEmpty(),
            city = api.city.orEmpty(),
            countryIcon = api.countryIcon.orEmpty(),
            competitionId = api.competitionId.orDefault(),
            competitionTitle = api.competitionTitle.orEmpty(),
            resultText = api.resultText.orEmpty(),
        )
    }

    fun fromApiToModel(api: ApiTestUserResultRow): TestUserResultRow {
        return TestUserResultRow(
            id = api.id.orDefault(),
            date = Instant.ofEpochSecond(api.date.orDefault()).atZone(ZoneId.systemDefault()).toLocalDate(),
            username = api.username.orEmpty(),
            testId = api.testId.orEmpty(),
            competitionTitle = api.competitionTitle.orEmpty(),
            place = api.place.orEmpty(),
            score = baseMapper.fromApiToModel(api.score),
        )
    }

    fun fromApiToModel(api: ApiTestUserResult): TestUserResult {
        return TestUserResult(
            id = api.id.orDefault(),
            date = Instant.ofEpochSecond(api.date.orDefault()).atZone(ZoneId.systemDefault()).toLocalDate(),
            username = api.username.orEmpty(),
            testId = api.testId.orEmpty(),
            competitionTitle = api.competitionTitle.orEmpty(),
            place = api.place.orEmpty(),
            score = baseMapper.fromApiToModel(api.score),
            spentTime = api.spentTime.orDefault(),
            answers = api.answers.orEmpty().map { fromApiToModel(it) },
        )
    }

    fun fromApiToModel(api: ApiAnswer): Answer {
        return Answer(
            id = api.id.orEmpty(),
            text = api.text.orEmpty(),
            image = api.image,
        )
    }

    fun fromApiToModel(api: ApiQuestion): Question {
        return when (api.type) {
            ApiQuestionType.LIST_ANSWER -> Question.ListAnswer(
                id = api.id.orDefault(),
                text = api.text.orEmpty(),
                image = api.image,
                answers = api.answers.orEmpty().map { fromApiToModel(it) },
            )
            ApiQuestionType.SINGLE_ANSWER -> Question.SingleAnswer(
                id = api.id.orDefault(),
                text = api.text.orEmpty(),
                image = api.image,
                label = api.label.orEmpty(),
            )
            null -> throw IllegalStateException("Unsupported question type from api")
        }
    }

    fun fromApiToModel(api: ApiCompetitionTest): CompetitionTest {
        return CompetitionTest(
            id = api.id.orEmpty(),
            title = api.title.orEmpty(),
            ageCategoryTitle = api.ageCategoryTitle,
            questions = api.questions.orEmpty().map { fromApiToModel(it) },
        )
    }

    fun fromApiToModel(api: ApiCreatedResult): CreatedResult {
        return CreatedResult(
            id = api.id.orDefault(),
            username = api.username.orEmpty(),
            resultLink = api.resultLink.orEmpty(),
            achievementText = api.achievementText,
        )
    }

    fun fromApiToModel(api: ApiTempResult): TempResult {
        return TempResult(
            id = api.id.orDefault(),
            answers = api.answers.orEmpty().map { fromApiToModel(it) },
            score = baseMapper.fromApiToModel(api.score),
            spentTime = api.spentTime.orDefault(),
            resultInfo = api.resultInfo?.let { fromApiToModel(it) },
        )
    }

    fun fromApiToModel(api: ApiResultInfo): ResultInfo {
        return ResultInfo(
            placeText = api.placeText.orEmpty(),
            averageScore = api.averageScore.orDefault(),
            resultText = api.resultText.orEmpty(),
        )
    }

    fun fromApiToModel(api: ApiResultAnswer): ResultAnswer {
        return ResultAnswer(
            question = fromApiToModel(api.question),
            answerText = api.answerText?.takeIf { it.isNotEmpty() },
            correct = fromApiToModel(api.correct),
        )
    }

    fun fromModelToApi(model: CompetitionPassData): CompetitionCheckParams {
        return CompetitionCheckParams(
            testId = model.testId,
            questionResults = model.questionResults.map { question ->
                when (question) {
                    is CompetitionPassData.Question.ListAnswer -> CompetitionCheckParams.Question.ListAnswer(
                        questionId = question.questionId,
                        answerId = question.answerId,
                    )
                    is CompetitionPassData.Question.SingleAnswer -> CompetitionCheckParams.Question.SingleAnswer(
                        questionId = question.questionId,
                        textAnswer = question.textAnswer,
                    )
                }
            },
            spentTime = model.spentTime,
        )
    }

    private fun fromApiToModel(api: ApiResultAnswer.Question?): ResultAnswer.Question {
        return ResultAnswer.Question(
            title = api?.title.orEmpty(),
            text = api?.text.orEmpty(),
        )
    }

    private fun fromApiToModel(api: ApiResultAnswer.Correction?): ResultAnswer.Correction {
        return ResultAnswer.Correction(
            answerText = api?.answerText.orEmpty(),
            isCorrect = api?.isCorrect.orDefault(),
        )
    }
}

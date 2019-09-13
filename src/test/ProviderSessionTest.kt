package test

import org.junit.Before
import org.junit.Test
import shared.DummySessionRepository
import shared.ISession
import shared.ISessionRepository
import simple.Session
import srp_solution.SessionFacade
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProviderSessionTest {
    companion object {
        const val DUMMY_USERNAME = "username"
        const val DUMMY_PASSWORD = "K0sm0$123"
        const val TYPE_TEST = ISession.PROVIDER
        val otherData = mapOf(Pair(ISessionRepository.PHONE_PARAM, "5529682090"))
    }

    private lateinit var session: ISession

    @Before
    fun configure() {
        session = SessionFacade(DummySessionRepository())
    }

    @Test
    fun username_validation() {
        val session = Session(DummySessionRepository())
        assertTrue(
            session.signup(
                "joseph",
                DUMMY_PASSWORD,
                TYPE_TEST,
                otherData
            )
        )
        assertTrue(
            session.signup(
                "joseph_s",
                DUMMY_PASSWORD,
                TYPE_TEST,
                otherData
            )
        )
        assertTrue(
            session.signup(
                "joseph.s",
                DUMMY_PASSWORD,
                TYPE_TEST,
                otherData
            )
        )
    }

    @Test
    fun password_validation() {
        assertTrue {
            session.signup(
                DUMMY_USERNAME,
                DUMMY_PASSWORD,
                TYPE_TEST,
                otherData
            )
        }
        assertFalse {
            session.signup(
                DUMMY_USERNAME, "",
                TYPE_TEST,
                otherData
            )
        }
        assertFalse {
            session.signup(
                DUMMY_USERNAME, "123456",
                TYPE_TEST,
                otherData
            )
        }
        assertFalse {
            session.signup(
                DUMMY_USERNAME, "%h0R",
                TYPE_TEST,
                otherData
            )
        }
    }

    @Test
    fun phoneValidation() {
        assertFalse {
            session.signup(
                DUMMY_USERNAME,
                DUMMY_PASSWORD,
                TYPE_TEST,
                mapOf()
            )
        }

        assertFalse {
            session.signup(
                DUMMY_USERNAME,
                DUMMY_PASSWORD,
                TYPE_TEST,
                mapOf(Pair(ISessionRepository.PHONE_PARAM, "+525529682090"))
            )
        }

        assertFalse {
            session.signup(
                DUMMY_USERNAME,
                DUMMY_PASSWORD,
                TYPE_TEST,
                mapOf(Pair(ISessionRepository.PHONE_PARAM, "+52552968209"))
            )
        }

        assertFalse {
            session.signup(
                DUMMY_USERNAME,
                DUMMY_PASSWORD,
                TYPE_TEST,
                mapOf(Pair(ISessionRepository.PHONE_PARAM, ""))
            )
        }

        assertFalse {
            session.signup(
                DUMMY_USERNAME,
                DUMMY_PASSWORD,
                TYPE_TEST,
                mapOf(Pair(ISessionRepository.PHONE_PARAM, "ABQIEOPD$$"))
            )
        }
    }
}
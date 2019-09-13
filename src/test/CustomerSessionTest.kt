package test

import org.junit.Before
import shared.DummySessionRepository
import org.junit.Test
import shared.ISession
import shared.ISessionRepository
import simple.Session
import srp_solution.SessionFacade
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CustomerSessionTest {
    companion object {
        const val DUMMY_USERNAME = "username"
        const val DUMMY_PASSWORD = "K0sm0$123"
        const val TYPE_TEST = ISession.CUSTOMER
        val otherData = mapOf(Pair(ISessionRepository.AGE_PARAM, 25))
    }

    lateinit var session: ISession

    @Before
    fun configure() {
        session = SessionFacade(DummySessionRepository())
    }

    @Test
    fun username_validation() {
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
        assertFalse(
            session.signup(
                "joseph.s",
                DUMMY_PASSWORD,
                TYPE_TEST,
                otherData
            )
        )
        assertFalse(
            session.signup(
                "J%",
                DUMMY_PASSWORD,
                TYPE_TEST,
                otherData
            )
        )
        assertFalse(
            session.signup(
                "",
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
                DUMMY_USERNAME, "P4$$&W0rd",
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
    }

    @Test
    fun ageValidation() {
        assertFalse {
            session.signup(
                ProviderSessionTest.DUMMY_USERNAME,
                ProviderSessionTest.DUMMY_PASSWORD,
                ProviderSessionTest.TYPE_TEST,
                mapOf()
            )
        }

        assertFalse {
            session.signup(
                ProviderSessionTest.DUMMY_USERNAME,
                ProviderSessionTest.DUMMY_PASSWORD,
                ProviderSessionTest.TYPE_TEST,
                mapOf(Pair(ISessionRepository.AGE_PARAM, "+"))
            )
        }
    }
}


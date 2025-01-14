package app.k9mail.feature.account.setup.ui

import app.k9mail.core.ui.compose.common.mvi.BaseViewModel
import app.k9mail.feature.account.setup.ui.AccountSetupContract.Effect
import app.k9mail.feature.account.setup.ui.AccountSetupContract.Event
import app.k9mail.feature.account.setup.ui.AccountSetupContract.SetupStep
import app.k9mail.feature.account.setup.ui.AccountSetupContract.State
import app.k9mail.feature.account.setup.ui.AccountSetupContract.ViewModel

class AccountSetupViewModel(
    initialState: State = State(),
) : BaseViewModel<State, Event, Effect>(initialState), ViewModel {

    override fun event(event: Event) {
        when (event) {
            Event.OnBack -> onBack()
            Event.OnNext -> onNext()
        }
    }

    private fun onBack() {
        when (state.value.setupStep) {
            SetupStep.AUTO_CONFIG -> navigateBack()
            SetupStep.MANUAL_CONFIG -> changeToSetupStep(SetupStep.AUTO_CONFIG)
            SetupStep.OPTIONS -> changeToSetupStep(SetupStep.MANUAL_CONFIG)
        }
    }

    private fun onNext() {
        when (state.value.setupStep) {
            SetupStep.AUTO_CONFIG -> changeToSetupStep(SetupStep.MANUAL_CONFIG)
            SetupStep.MANUAL_CONFIG -> changeToSetupStep(SetupStep.OPTIONS)
            SetupStep.OPTIONS -> navigateNext()
        }
    }

    private fun changeToSetupStep(setupStep: SetupStep) {
        updateState {
            it.copy(
                setupStep = setupStep,
            )
        }
    }

    private fun navigateNext() {
        // TODO: validate account

        emitEffect(Effect.NavigateNext)
    }

    private fun navigateBack() = emitEffect(Effect.NavigateBack)
}

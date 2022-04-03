<script lang="ts">
    import { session } from "$app/stores"
    import Button from "$lib/components/Button.svelte"
    import { AuthApi } from "$lib/core/auth/AuthApi"
    import { COOKIE_JWT } from "$lib/core/cookies"
    import Cookies from "js-cookie"
    import { createEventDispatcher } from "svelte"
    import { container } from "tsyringe"

    export let username: string

    const dispatch = createEventDispatcher()
    const api = container.resolve(AuthApi)
    let password: string = ""
    let passwordRepeat: string = ""
    let clientError: string = ""
    let serverError: string = ""
    let info: string = ""

    $: submitEnabled = Boolean(password) && Boolean(passwordRepeat) && !clientError && !serverError

    $: {
        if (Boolean(password) && password.length < 8) {
            clientError = "Пароль должен содержать как минимум 8 символов."
        } else if (password != passwordRepeat) {
            clientError = "Пароли не совпадают."
        } else {
            clientError = ""
        }
    }

    function submit() {
        info = "Проверяем..."
        api.signUp({ username, password })
            .then((response) => {
                Cookies.set(COOKIE_JWT, response.jwt)
                session.update((session) => {
                    session.user = response
                    return session
                })
                dispatch("success")
            })
            .catch((e) => (serverError = e))
            .then(() => (info = ""))
    }

    function passwordChanged() {
        serverError = ""
    }

    function cancel() {
        dispatch("cancel")
    }
</script>

<p class="text-2xl font-bold inline-block p-2 hover:cursor-pointer" on:click={cancel}>←</p>
<p class="text-center text-xl mb-4 w-full">
    Добро пожаловать, {username}! Для окончания регистрации придумайте пароль.
</p>
<input
    type="password"
    autocomplete="new-password"
    bind:value={password}
    on:input={passwordChanged}
    placeholder="Пароль"
    class="block w-full border-2 border-solid rounded-sm border-gray-500 min-w-[10rem] p-2 mb-4"
/>
<input
    type="password"
    autocomplete="new-password"
    bind:value={passwordRepeat}
    on:input={passwordChanged}
    placeholder="Повтор пароля"
    class="block w-full border-2 border-solid rounded-sm border-gray-500 min-w-[10rem] p-2 mb-4"
/>
{#if clientError || serverError}
    <p class="text-center text-sm mb-4 text-red-600">{clientError || serverError}</p>
{/if}
<div class="text-right">
    {#if info}
        <span class="text-gray-500">{info}</span>
    {:else}
        <Button enabled={submitEnabled} on:click={submit}>Далее</Button>
    {/if}
</div>

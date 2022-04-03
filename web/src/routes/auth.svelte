<script lang="ts" context="module">
    import { goto } from "$app/navigation"
    import { page } from "$app/stores"
    import CheckUsername from "$lib/components/auth/CheckUsername.svelte"
    import SignIn from "$lib/components/auth/SignIn.svelte"
    import SignUp from "$lib/components/auth/SignUp.svelte"
    import Title from "$lib/components/Title.svelte"
    import type { Load } from "@sveltejs/kit"
    import { StatusCodes } from "http-status-codes"

    export const REDIRECT_TO = "redirect_to"

    export const load: Load = ({ session, url }) =>
        session.user
            ? {
                  status: StatusCodes.TEMPORARY_REDIRECT,
                  redirect: url.searchParams.get(REDIRECT_TO) || "/"
              }
            : {
                  status: StatusCodes.OK
              }
</script>

<script lang="ts">
    let username: string = ""
    let userExists: boolean = false

    function signIn(event: CustomEvent<string>) {
        username = event.detail
        userExists = true
    }

    function signUp(event: CustomEvent<string>) {
        username = event.detail
        userExists = false
    }

    function cancel() {
        username = ""
    }

    function success() {
        goto($page.url.searchParams.get(REDIRECT_TO) || "/")
    }
</script>

<Title caption="Авторизация" />

<div class="h-full">
    <div class="p-4 flex-1 max-w-lg m-auto">
        {#if username}
            {#if userExists}
                <SignIn {username} on:cancel={cancel} on:success={success} />
            {:else}
                <SignUp {username} on:cancel={cancel} on:success={success} />
            {/if}
        {:else}
            <CheckUsername on:signIn={signIn} on:signUp={signUp} />
        {/if}
    </div>
</div>

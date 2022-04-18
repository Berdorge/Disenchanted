<script lang="ts">
    import type { Program } from "$lib/program/Program"
    import { UniversityProgramApi } from "$lib/university/UniversityProgramApi"
    import { notEmpty } from "$lib/utils/Array"
    import { container } from "tsyringe"

    export let universityId: string

    let loading = true
    let programs: Array<Program>

    $: {
        const api = container.resolve(UniversityProgramApi)
        api.getPrograms(universityId)
            .then((apiPrograms) => (programs = apiPrograms))
            .then(() => (loading = false))
    }
</script>

{#if loading}
    <p class="text-gray-800 text-sm text-center">Загружаем...</p>
{:else if notEmpty(programs)}
    <h2 class="text-xl text-gray-800 text-center p-2">Направления</h2>
    {#each programs as program}
        <a
            href="/universities/{universityId}/programs/{program.id}"
            class="text-center block underline p-1"
        >
            {program.id}. {program.name}
        </a>
    {/each}
{/if}

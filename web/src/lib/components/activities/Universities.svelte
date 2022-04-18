<script lang="ts">
    import { UniversityProgramActivityApi } from "$lib/activity/UniversityProgramActivityApi"
    import type { University } from "$lib/university/University"
    import { notEmpty } from "$lib/utils/Array"
    import { container } from "tsyringe"

    export let activityId: string

    let loading = true
    let universities: Array<University>

    $: {
        const api = container.resolve(UniversityProgramActivityApi)
        api.getActivityUniversities(activityId)
            .then((apiActivities) => (universities = apiActivities))
            .then(() => (loading = false))
    }
</script>

{#if loading}
    <p class="text-gray-800 text-sm text-center">Загружаем...</p>
{:else if notEmpty(universities)}
    <h2 class="text-xl text-gray-800 text-center p-2">Университеты</h2>
    {#each universities as university}
        <a href="/universities/{university.id}" class="flex justify-between items-center p-2">
            <img class="w-16 h-16 mr-4" src={university.logoUrl} alt="Логотип" />
            <p class="flex-1 underline text-center text-l">{university.name}</p>
        </a>
    {/each}
{/if}

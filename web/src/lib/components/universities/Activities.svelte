<script lang="ts">
    import type { DescribedActivity } from "$lib/activity/DescribedActivity"
    import { UniversityProgramActivityApi } from "$lib/activity/UniversityProgramActivityApi"
    import ActivityDescription from "$lib/components/universities/ActivityDescription.svelte"
    import { notEmpty } from "$lib/utils/Array"
    import { container } from "tsyringe"

    export let universityId: string
    export let programId: string | null = null

    let loading = true
    let activities: Array<DescribedActivity>

    $: {
        const api = container.resolve(UniversityProgramActivityApi)
        const promise =
            programId === null
                ? api.getUniversityActivities(universityId)
                : api.getUniversityProgramActivities(universityId, programId)
        promise.then((apiActivities) => (activities = apiActivities)).then(() => (loading = false))
    }
</script>

{#if loading}
    <p class="text-gray-800 text-sm text-center">Загружаем...</p>
{:else if notEmpty(activities)}
    <h2 class="text-xl text-gray-800 text-center p-2">Активности</h2>
    {#each activities as activity}
        <ActivityDescription {activity} />
    {/each}
{/if}

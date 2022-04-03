/// <reference types="@sveltejs/kit" />

declare namespace App {
    interface Locals {
        user?: import("$lib/core/user/User").User
    }
    interface Session {
        user?: import("$lib/core/user/User").User
    }
}

export async function getEvents() {
    try {
        const response = await fetch("http://localhost:8080/api/events/get");
        if (!response.ok) {
            throw new Error("Failed to fetch events");
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching events:", error);
        throw error;
    }
}

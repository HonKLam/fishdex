export default function formatDate(date) {
    return new Date(date).toLocaleString('de-DE').split(',')[0]
}

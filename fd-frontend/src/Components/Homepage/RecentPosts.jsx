import styles from '../../css/RecentPosts.module.css'
import Button from '../General/Button'
import RecentActivity from './RecentActivity'
import PropTypes from 'prop-types'
import { fetchData } from '../../utils/api'
import { useQuery } from 'react-query'

RecentPosts.propTypes = {
    user: PropTypes.object,
}

export default function RecentPosts(props) {
    const { user } = props

    const { data, isLoading, isError } = useQuery('diary-data', () =>
        fetchData('/diary')
    )

    let last3Elements

    if (isLoading) return <div></div>
    if (isError) return <div></div>
    if (data) {
        last3Elements = data.slice(-3)
    }

    function handleClick() {
        window.location.pathname = '/timeline'
    }

    return (
        <div className={styles.main_container}>
            <h3>Meine letzten Aktvitäten</h3>
            {data &&
                last3Elements.map((entry, index) => {
                    return (
                        <RecentActivity user={user} entry={entry} key={index} />
                    )
                })}

            <div id={styles.timeline_button}>
                <Button text="Timeline" callBack={handleClick} />
            </div>
        </div>
    )
}

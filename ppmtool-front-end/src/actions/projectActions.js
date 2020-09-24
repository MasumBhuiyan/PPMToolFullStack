import axios from 'axios';
import { DELETE_PROJECT, GET_ERRORS, GET_PROJECT, GET_PROJECTS } from './types'

export const createProject = (project, history) => async dispatch => {
    try {
        const response = await axios.post('/api/project', project);
        history.push('/dashboard');
        dispatch({
            type: GET_ERRORS,
            payload: {}
        }) 
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })   
    }
}

export const findAllProjects = () => async dispatch => {
	const response = await axios.get('/api/project/all');
	dispatch({
		type: GET_PROJECTS,
		payload: response.data
	});
};

export const findProject = (projectIdentifier, history) => async dispatch => {
    try {
        const response = await axios.get(`/api/project/${projectIdentifier}`);
        dispatch({
            type: GET_PROJECT,
            payload: response.data
        })
    } catch (error) {
        history.push('/dashborad');
    }
}

export const deleteProject = projectIdentifier => async dispatch => {
    if( window.confirm("Are you sure? This will delete the project and all the data related to it.")){
        await axios.delete(`/api/project/${projectIdentifier}`);
        dispatch({
            type: DELETE_PROJECT,
            payload: projectIdentifier
        })
    }
}